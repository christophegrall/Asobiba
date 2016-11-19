package net.orekyuu.controller;

import net.orekyuu.controller.model.RoomImageMessageType;
import net.orekyuu.repository.dao.RoomImageDao;
import net.orekyuu.repository.entity.RoomImage;
import net.orekyuu.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

@Controller
@RequestMapping("/room/{id}/image")
@Transactional(readOnly = true)
public class RoomImageController {

    @Autowired
    private RoomImageDao imageDao;
    @Autowired
    private PushNotificationService pushService;

    @GetMapping
    public void findRoomImage(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "image/*");

        byte[] data = imageDao.findByRoom(id).map(RoomImage::getData)
                .orElseGet(() -> {
                    try(InputStream in = getClass().getClassLoader().getResourceAsStream("no_image.png")) {
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        FileCopyUtils.copy(in, outputStream);
                        return outputStream.toByteArray();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        response.getOutputStream().write(data);
    }

    @Transactional(readOnly = false)
    @PostMapping
    @ResponseBody
    public String updateImage(@PathVariable("id") long id, @RequestParam("file")MultipartFile image) throws IOException {
        RoomImage roomImage = imageDao.findByRoom(id).orElse(null);
        if (roomImage == null) {
            imageDao.insert(new RoomImage(id, image.getBytes()));
        } else {
            RoomImage data = new RoomImage(roomImage.getRoomId(), image.getBytes());
            imageDao.update(data);
        }
        pushService.pushAll(RoomImageMessageType.ROOM_IMAGE_UPDATE, id);
        return "ok";
    }
}
