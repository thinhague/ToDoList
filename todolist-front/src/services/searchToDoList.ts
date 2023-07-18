import { TodoListPagination } from "@/types/todolist";
import axios from "./axios"
import { AxiosResponse } from 'axios';
import { QueryParams } from "@/types/requests";

export const searchTask = async (queryParams?:QueryParams) => {
  let endpoint: string

  switch (queryParams) {
    case 'checked':
      endpoint = `/todolist/search?sort=createdAt,DESC&isChecked=true`
      break;
    case 'unchecked':
      endpoint = `/todolist/search?sort=createdAt,DESC&isChecked=false`
      break;

    default:
      endpoint = '/todolist/search?sort=createdAt,DESC'
  }

  const response: AxiosResponse<TodoListPagination> = await axios.get(endpoint);
  const data = response.data;
  return data;
}

export const getTaskById = async (taskId: number) => {
  const response: AxiosResponse<TodoListPagination> = await axios.get('/todolist/' + taskId);
  const data = response.data;
  return data;
}