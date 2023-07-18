import axios from "./axios"

export const deleteTask = async (taskId:number) => {
  await axios.delete(`/todolist/${taskId}/delete`);
}
