import { Role } from "@/lib/authorization";

export type BaseEntity = {
  id: string;
};

export type Entity<T> = {
  [K in keyof T]: T[K];
} & BaseEntity;

// Also returning from the login
export type User = Entity<{
  firstName: string;
  lastName: string;
  email: string;
  role: Role;
}>;

export type Chat = Entity<{
  title: string;
  createdAt: string;
}>;

export enum MessageType {
  PROMPT = "PROMPT",
  RESPONSE = "RESPONSE"
}

export type Message = Entity<{
  content: string;
  createdAt: string;
  type: MessageType;
}>