import http from "./http";

export const create = (data) => {
  return http({
    method: 'POST',
    headers:{
      "Content-Type":"multipart/form-data"
    },
    url: '/problem/create',
    data
  })
}

export const hot = () => {
  return http({
    method: 'GET',
    url: '/problem/hot'
  })
}

export const getRecommended = (data) => {
  return http({
    method: 'GET',
    url: '/problem/recommend',
    params: data
  })
}

export const getProblemById = (id) => {
  return http({
    method: 'GET',
    url: `/problem/${id}`
  })
}

export const getProblemByUserId = (userId) => {
  return http({
    method: 'GET',
    url: `/problem/u/${userId}`
  })
}

export const getProblemsByUserId = (userId,paging) => {
  return http({
    method: 'GET',
    url: `/problem/mine/${userId}`,
    params:paging
  })
}

export const getFollowedUserProblems = (userId,paging) => {
  return http({
    method: 'GET',
    url: `/problem/followedUserProblems/${userId}`,
    params:paging
  })
}

export const delProblem = (id) => {
  return http({
    method: 'DELETE',
    url: `/problem/${id}`
  })
}

export const getProblemYouLike = (userId) => {
  return http({
    method: 'GET',
    url: `/problem/wenda/like`,
    params:{
      userId
    }
  })
}

export const getPopularProblems = () => {
  return http({
    method: 'GET',
    url: `/problem/wenda/popular`
  })
}

export const getLatestProblems = () => {
  return http({
    method: 'GET',
    url: `/problem/wenda/latest`
  })
}