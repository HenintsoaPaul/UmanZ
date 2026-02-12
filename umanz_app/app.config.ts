export default defineAppConfig({
    ui: {
        primary: 'umanz-purple',
        gray: 'slate',
        button: {
            rounded: 'rounded-xl',
            default: {
                size: 'md',
                color: 'umanz-purple',
                variant: 'solid'
            }
        },
        input: {
            rounded: 'rounded-xl',
            default: {
                size: 'md',
                color: 'gray'
            }
        },
        select: {
            rounded: 'rounded-xl'
        },
        textarea: {
            rounded: 'rounded-xl'
        },
        card: {
            rounded: 'rounded-2xl',
            shadow: 'shadow-sm',
            border: 'border-gray-100'
        },
        modal: {
            rounded: 'rounded-2xl'
        },
        table: {
            wrapper: 'rounded-xl border border-gray-100 overflow-hidden',
            thead: 'bg-gray-50/50',
            th: {
                color: 'text-gray-900',
                font: 'font-semibold'
            },
            tbody: 'divide-y divide-gray-100',
            tr: {
                selected: 'bg-gray-50'
            }
        }
    }
})
