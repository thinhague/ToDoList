import { useEffect, useState } from 'react'
import { List } from '@mantine/core';
import type { Task as ITask } from '../../types/todolist'
import { Task } from '../Task';
import { AddTaskBar } from '../AddTaskBar';
import { FilterTask } from '../FilterTask';

interface IListTasks {
  data: ITask[]
}

export const DashboardTasks = ({ data }: IListTasks) => {
  const [tasks, setTasks] = useState<ITask[]>([])
  useEffect(() => {setTasks(data)},[])

  return (
    <>
      <AddTaskBar setTasks={setTasks} />
      <FilterTask setTasks={setTasks} />
      <List
        w={'100%'}
        spacing="lg"
        size="sm"
        center
      >
        {tasks?.map((task) => <Task key={task.id} {...task} setTasks={setTasks} />)}
      </List>
    </>
  )
}
