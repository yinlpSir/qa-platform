// @ts-check
import { defineStore } from 'pinia'
import { ref } from 'vue'

export default defineStore('problem', {
    state: () => {
        const publishProblemVisible = ref(false)
        return {
            publishProblemVisible
        }
    }
})
