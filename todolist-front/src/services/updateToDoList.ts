import { Task } from "@/types/todolist";
import axios from "./axios"
import { AxiosResponse } from 'axios';
import { UpdateTaskRequest } from "@/types/requests";

export const updateTask = async (taskId: number,body: UpdateTaskRequest) => {
  const response: AxiosResponse<Task> = await axios.put(`/todolist/${taskId}/update`, body);
  const data = response.data;
  return data;
}