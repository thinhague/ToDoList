import { Task } from "@/types/todolist";
import axios from "./axios"
import { AxiosResponse } from 'axios';
import { CreateTaskRequest } from "@/types/requests";

export const createTask = async (body:CreateTaskRequest) => {
  const response: AxiosResponse<Task> = await axios.post('/todolist/create', body);
  const data = response.data;
  return data;
}