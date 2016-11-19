
export class ChatMessage {
    constructor(
        public commentId: number,
        public roomId: number,
        public comment: string,
        public userName: string,
        public commentedAt: string
    ) {}
}
