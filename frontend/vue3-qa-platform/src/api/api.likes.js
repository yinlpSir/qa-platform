import http from "./http";

export const getLikeCount = (problemId) => {
    return http({
        method: 'GET',
        url: '/likes/count',
        params: { problemId }
    })
}

export const checkLikeStatus = (userId,problemId) => {
    return http({
        method: 'GET',
        url: '/likes/check',
        params: {userId, problemId }
    })
}

export const like = (userId,problemId) => {
    return http({
        method: 'GET',
        url: '/likes/click',
        params: {userId, problemId }
    })
}

export const unlike = (userId,problemId) => {
    return http({
        method: 'DELETE',
        url: '/likes',
        params: {userId, problemId }
    })
}