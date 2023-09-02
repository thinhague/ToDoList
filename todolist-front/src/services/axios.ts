import axios from "axios";

export default axios.create({
  baseURL: process.env.NEXT_PUBLIC_URL
})

export const axiosSSR = axios.create({
  baseURL: process.env.NEXT_PUBLIC_URL_SSR
})