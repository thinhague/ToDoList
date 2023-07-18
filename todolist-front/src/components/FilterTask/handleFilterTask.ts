import type { FilterBy } from "."
import { searchTask } from "@/services/searchToDoList"
export const handleFilterTask = async (filterBy?: FilterBy) => {
  const tasks = await searchTask(filterBy)
  return tasks;
}