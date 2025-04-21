import http from "./http";

export const getFollowedUsers = (userId) => {
  return http({
    method: 'GET',
    url: '/follow/getFollowedUsers',
    params: { userId }
  })
}

export const getFans = (userId) => {
  return http({
    method: 'GET',
    url: '/follow/getFollowers',
    params: { userId }
  })
}

export const getFollowedCount = (userId) => {
  return http({
    method: 'GET',
    url: '/follow/followedCount',
    params: { userId }
  })
}

export const getFollowersCount = (userId) => {
  return http({
    method: 'GET',
    url: '/follow/followersCount',
    params: { userId }
  })
}

export const isUserFollowed = (userId,checkedUserId) => {
  return http({
    method: 'GET',
    url: '/follow/check',
    params: { userId,checkedUserId }
  })
}

/**
 * 关注
 * @param {*} userId 
 * @param {*} followedId 
 * @returns 
 */
export const follow = (userId,followedId) => {
  return http({
    method: 'GET',
    url: '/follow',
    params: { userId,followedId }
  })
}

/**
 * 取消关注
 */
export const unFollow = (userId,followedId) => {
  return http({
    method: 'DELETE',
    url: `/follow/${userId}/${followedId}`
  })
}