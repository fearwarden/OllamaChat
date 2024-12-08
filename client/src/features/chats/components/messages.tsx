import {Message, MessageType} from "@/types/api.ts";
import {ScrollArea} from "@/components/ui/scroll-area.tsx";
import {Avatar, AvatarFallback} from "@/components/ui/avatar.tsx";
import {MarkdownRenderer} from "@/features/chats/components/markdown-renderer.tsx";
import {CircleArrowUp} from "lucide-react";
import {Input} from "@/components/ui/input.tsx";

export interface MessagesProps {
    data: Message[] | null | undefined
}

export const Messages = ({data}: MessagesProps) => {
    return (
        <div className="flex flex-col h-screen">
            <ScrollArea className="flex-grow p-6 h-1/2">
                <div className="spacey-4 max-w-3xl mx-auto">
                    {data?.map((message) => (
                        <div key={message.id}
                             className={`flex ${message.type === MessageType.PROMPT ? 'justify-end' : 'justify-start'}`}>
                            <div
                                className={`flex items-start m-3 space-x-2 max-w-[70%] ${message.type === MessageType.PROMPT ? 'flex-row-reverse space-x-reverse' : ''}`}>
                                <Avatar className="w-8 h-8">
                                    <AvatarFallback>{message.type === MessageType.PROMPT ? 'U' : 'AI'}</AvatarFallback>
                                </Avatar>
                                <div
                                    className={`rounded-lg p-3 ${message.type === MessageType.PROMPT ? 'bg-accent text-white' : 'text-white'}`}>
                                    <MarkdownRenderer content={message.content}/>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </ScrollArea>
            <div className="absolute bottom-20 left-0 right-0 p-4 bg-gradient-to-t from-background to-background/0 pt-6">
                <div className="relative max-w-3xl mx-auto">
                    <Input
                        type="text"
                        placeholder="Message Ollama"
                        className="rounded-full w-full h-14 px-6 text-md bg-background border-muted-foreground/20 focus-visible:ring-accent focus-visible:ring-offset-2 pr-12"
                    />
                    <button
                        className="absolute right-2 top-1/2 transform -translate-y-1/2 p-2 rounded-full hover:bg-muted transition-colors">
                        <CircleArrowUp className="text-muted-foreground w-6 h-6"/>
                    </button>
                </div>
            </div>
        </div>
    )
}