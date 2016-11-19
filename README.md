# あそびば
学祭ハッカソンで作ったWebｱｯﾋﾟです。
放課後の教室の利用状況を共有できるよ！
ex) E404教室でカードゲームするよ　など

## フレームワークとか
フロント: Angular2
バック: SpringBoot

## ビルド方法

```
cd front
npm install
npm build

cd ../
./mvnw package
```

## 開発時のサーバーの建て方

### フロント
```
cd front
npm start
```
↑のコマンドを実行するとdev-serverが8081ポートで起動する

### バック
```
./mvnw spring-boot:run
```
↑のコマンドを実行すると8080ポートで起動する

動作確認は8081のdev-serverで行う。
proxyの設定がしてあるので、APIもちゃんと叩けているはず。たぶん。
