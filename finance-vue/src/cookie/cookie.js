import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'
const admin = 'admin'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function getadmin() {
  return Cookies.get(admin)
}

export function setadmin(Admin) {
  return Cookies.set(admin, Admin)
}

export function removeadmin() {
  return Cookies.remove(admin)
}