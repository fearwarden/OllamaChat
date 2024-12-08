import ReactMarkdown from 'react-markdown'
import remarkGfm from 'remark-gfm'
import { cn } from "@/lib/utils"

interface MarkdownRendererProps {
    content: string;
    className?: string
}

export function MarkdownRenderer({content, className}: MarkdownRendererProps) {
    return (
        <ReactMarkdown
            remarkPlugins={[remarkGfm]}
            components={{
                // eslint-disable-next-line @typescript-eslint/no-unused-vars
                h1: ({ node, ...props }) => <h1 className="scroll-m-20 text-4xl font-extrabold tracking-tight lg:text-5xl" {...props} />,
                // eslint-disable-next-line @typescript-eslint/no-unused-vars
                h2: ({ node, ...props }) => <h2 className="scroll-m-20 border-b pb-2 text-3xl font-semibold tracking-tight transition-colors first:mt-0" {...props} />,
                // eslint-disable-next-line @typescript-eslint/no-unused-vars
                h3: ({ node, ...props }) => <h3 className="scroll-m-20 text-2xl font-semibold tracking-tight" {...props} />,
                // eslint-disable-next-line @typescript-eslint/no-unused-vars
                h4: ({ node, ...props }) => <h4 className="scroll-m-20 text-xl font-semibold tracking-tight" {...props} />,
                // eslint-disable-next-line @typescript-eslint/no-unused-vars
                p: ({ node, ...props }) => <p className="leading-7 [&:not(:first-child)]:mt-6" {...props} />,
                // eslint-disable-next-line @typescript-eslint/no-unused-vars
                ul: ({ node, ...props }) => <ul className="my-6 ml-6 list-disc [&>li]:mt-2" {...props} />,
                // eslint-disable-next-line @typescript-eslint/no-unused-vars
                ol: ({ node, ...props }) => <ol className="my-6 ml-6 list-decimal [&>li]:mt-2" {...props} />,
                // eslint-disable-next-line @typescript-eslint/no-unused-vars
                li: ({ node, ...props }) => <li className="mt-2" {...props} />,
                // eslint-disable-next-line @typescript-eslint/no-unused-vars
                blockquote: ({ node, ...props }) => <blockquote className="mt-6 border-l-2 pl-6 italic" {...props} />,
                // eslint-disable-next-line @typescript-eslint/no-unused-vars
                a: ({ node, ...props }) => <a className="font-medium text-primary underline underline-offset-4" {...props} />,
                // eslint-disable-next-line @typescript-eslint/no-unused-vars
                code: ({ node, className, children, ...props }) => {
                    const match = /language-(\w+)/.exec(className || '')
                    // @ts-ignore
                    const isInline = !match && !children.includes('\n')
                    return isInline ? (
                        <code className="relative rounded bg-muted px-[0.3rem] py-[0.2rem] font-mono text-sm font-semibold" {...props}>
                            {children}
                        </code>
                    ) : (
                        <pre className="relative rounded bg-muted p-4 my-4 overflow-x-auto">
              <code className="font-mono text-sm" {...props}>
                {children}
              </code>
            </pre>
                    )
                },
            }}
            className={cn("space-y-6", className)}
        >
            {content}
        </ReactMarkdown>
    )
}