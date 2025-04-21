import http from "./http";

export const collectionProblem = (userId,problemId) => {
    return http({
        method: 'GET',
        url: '/collection',
        params: { userId,problemId }
    })
}

export const checkProblemCollected = (userId,problemId) => {
    return http({
        method: 'GET',
        url: '/collection/isUserCollectedProblem',
        params: { userId,problemId }
    })
}

export const cancelCollectionProblem = (userId,problemId) => {
    return http({
        method: 'DELETE',
        url: `/collection`,
        params: { userId,problemId }
    })
}

export const getCollectByUserId = (userId,paging) => {
    return http({
        method: 'GET',
        url: `/collection/getCollectedByUserId/${userId}`,
        params: paging
    })
}

export const getCountByUserId = (userId) => {
    return http({
        method: 'GET',
        url: `/collection/count`,
        params: {userId}
    })
}

export const getCountByProblemId = (problemId) => {
    return http({
        method: 'GET',
        url: `/collection/p/count`,
        params: {problemId}
    })
}