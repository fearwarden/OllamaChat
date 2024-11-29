import {useForm} from "react-hook-form";
import {Link} from "react-router-dom";
import {zodResolver} from "@hookform/resolvers/zod";

import {loginDefaultValues, LoginInput, LoginInputSchema, useLogin,} from "@/lib/auth";
import {Button} from "@/components/ui/button";
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage,} from "@/components/ui/form";
import {Input} from "@/components/ui/input";

type LoginFormProps = {
    onSuccess: () => void;
};

export const LoginForm = ({onSuccess}: LoginFormProps) => {
    const login = useLogin({onSuccess});

    const form = useForm<LoginInput>({
        resolver: zodResolver(LoginInputSchema),
        defaultValues: loginDefaultValues,
    });

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(login.mutate)} className="grid gap-4">
                <div className="grid gap-2">
                    <FormField
                        control={form.control}
                        name="email"
                        render={({field}) => (
                            <FormItem className="text-left">
                                <FormLabel>Email</FormLabel>
                                <FormControl>
                                    <Input type="email" placeholder="m@example.com" {...field} />
                                </FormControl>
                                <FormMessage/>
                            </FormItem>
                        )}
                    />
                </div>
                <div className="grid gap-2">
                    <FormField
                        control={form.control}
                        name="password"
                        render={({field}) => (
                            <FormItem>
                                <div className="flex justify-between">
                                    <FormLabel>Password</FormLabel>
                                    <FormLabel className="underline">
                                        {<Link to={"#"}>Forgot your password?</Link>}
                                    </FormLabel>
                                </div>
                                <FormControl>
                                    <Input type="password" {...field} />
                                </FormControl>
                                <FormMessage/>
                            </FormItem>
                        )}
                    />
                </div>
                <div className="grid gap-2">
                    <Button type="submit">Login</Button>
                </div>
                <div className="grid gap-2">
                    <h1>
                        Don't have an account?{" "}
                        {
                            <Link to={"#"} className="underline">
                                Sign up!
                            </Link>
                        }
                    </h1>
                </div>
            </form>
        </Form>
    );
};
