import http from "./http";

export const login = (data) => {
  return http({
    method: 'POST',
    url: '/user/login',
    data
  })
}

export const register = (data) => {
  return http({
    method: 'POST',
    url: '/user/register',
    data
  })
}

export const getCurrentUser = () => {
  return http({
    method: 'GET',
    url: '/user/getCurrentUser',
  })
}

export const getHeadPortrait = (userId) => {
  return http({
    method: 'GET',
    url: '/user/getHeadPortrait',
    params: {
      userId
    }
  })
}

export const modifyHeadPortrait = (userId,file) => {
  return http({
    method: 'PUT',
    url: '/user/modifyHeadPortrait',
    headers:{
      'Content-Type':'multipart/form-data'
    },
    data:{
      userId,
      headPortrait:file,
    }
  })
}

export const modifyUserBaseInfo = (data) => {
  return http({
    method: 'PUT',
    headers:{
      "Content-Type":"application/json"
    },
    url: '/user/modifyBaseInfo',
    data
  })
}

export const getRecommendedUser = () => {
  return http({
    method: 'GET',
    url: '/user/recommended'
  })
}

export const getUserById = (userId) => {
  return http({
    method: 'GET',
    url: '/user/getUserById',
    params:{userId}
  })
}