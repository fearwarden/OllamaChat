import {createContext} from "react";
import {SelectedChatContextType} from "@/types/context.ts";

export const SelectedChatContext = createContext<SelectedChatContextType | null>(null);