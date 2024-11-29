import {Message} from "@/types/api.ts";

export interface MessagesProps {
    data: Message[] | null | undefined
}

export const Messages = ({data}: MessagesProps) => {
    return (
        <>
            <h1>{}</h1>
            {data && data.map((message) => (
                <div className="flex flex-col gap-2">
                    <h1>{message.content}</h1>
                </div>
            ))}
        </>
    )
}