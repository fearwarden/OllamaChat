import { Role } from "@/lib/authorization";
import { UUID } from "crypto";

export type BaseEntity = {
  id: UUID;
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
