<template>
    <div class="min-h-screen pb-20">
        <div class="space-y-8 animate-pulse">
            <div class="max-w-3xl mx-auto px-4 pt-8">
                <div class="bg-white rounded-[2rem] border border-slate-100 p-8 flex flex-col md:flex-row items-center gap-10">
                    <USkeleton class="w-24 h-24 md:w-32 md:h-32 rounded-2xl shrink-0" />
                    <div class="flex-grow space-y-4 w-full">
                        <USkeleton class="h-10 w-3/4 mx-auto md:mx-0" />
                        <USkeleton class="h-6 w-1/2 mx-auto md:mx-0" />
                    </div>
                </div>
            </div>
            
            <div class="max-w-3xl mx-auto space-y-6 px-4">
                <USkeleton v-for="i in 3" :key="i" class="h-48 w-full rounded-[2rem]" />
            </div>
        </div>
    </div>
</template>