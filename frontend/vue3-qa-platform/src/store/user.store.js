// @ts-check
import { defineStore } from 'pinia'
import { ref } from 'vue'

export default defineStore('user', {
    state: () => {
        const headPortrait = ref('') // 头像

        const followedNumber = ref(0) // 关注数

        const followerNumber = ref(0) // 粉丝数
        
        const setHeadPortrait = (value) => {
            headPortrait.value = value
        }

        const setFollowedNumber = (value) => {
            followedNumber.value = value
        }

        const setFollowerNumber = (value) => {
            followerNumber.value = value
        }

        return {
            headPortrait,
            setHeadPortrait,
            followedNumber,
            setFollowedNumber,
            followerNumber,
            setFollowerNumber,
        }
    }
})
