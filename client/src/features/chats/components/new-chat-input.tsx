import {Input} from "@/components/ui/input.tsx";
import {CircleArrowUp} from "lucide-react";

export interface ChatInputProps {
    isNewChat: boolean;
}

export const NewChatInput = ({isNewChat}: ChatInputProps) => {

    return (
        <>
            {isNewChat ?
                <div className="flex flex-col gap-4">
                <h1 className="text-3xl font-semibold">What can I help you with?</h1>
                <div className="relative">
                    <Input
                        type="text"
                        placeholder="Message Ollama"
                        className="rounded-full w-[48rem] h-14 px-6 text-md bg-primary-foreground focus-visible:ring-sidebar"
                    />
                    <CircleArrowUp className="absolute right-6 top-1/2 transform -translate-y-1/2 text-gray-400"/>
                </div></div> : <></>}
        </>
    )
}