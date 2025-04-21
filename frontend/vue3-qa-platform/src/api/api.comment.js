import http from "./http";

export const addComment = (data) => {
    return http({
        method: 'POST',
        url: '/comment',
        data
    })
}

export const getComments = (problemId) => {
    return http({
        method: 'GET',
        url: '/comment',
        params:{
            problemId
        }
    })
}

export const getCommentCount = (problemId) => {
    return http({
        method: 'GET',
        url: '/comment/count',
        params:{
            problemId
        }
    })
}

export const getLatelyCommentCount = (problemId) => {
    return http({
        method: 'GET',
        url: '/comment/lately/count',
        params:{
            problemId
        }
    })
}


export const getCommentCountByUserId = (userId) => {
    return http({
        method: 'GET',
        url: '/comment/count/u',
        params:{
            userId
        }
    })
}

export const getCommentsByUserId = (userId,paging) => {
    return http({
        method: 'GET',
        url: `/comment/getCommentsByUserId/${userId}`,
        params:paging
    })
}

export const delComment = (commentId) => {
    return http({
        method: 'DELETE',
        url: `/comment/${commentId}`
    })
}