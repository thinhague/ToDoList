import { Input, Loader } from '@mantine/core';
import { notifications } from '@mantine/notifications';
import { BsPlusCircle } from 'react-icons/bs'
import styles from './TaskBar.module.css'
import { ChangeEvent, Dispatch, SetStateAction, useState } from 'react'
import { createTask } from '@/services/postTodoList'
import { Task } from '@/types/todolist';

interface ITaskBar {
  setTasks: Dispatch<SetStateAction<Task[]>>;
}

export const AddTaskBar = ({ setTasks }:ITaskBar) => {
  const [loading, setLoading] = useState(false)
  const [task, setTask] = useState('')

  const handleOnChange = (event: ChangeEvent<HTMLInputElement>) => setTask(event.target.value)
  
  const handleCreateTask = async () => {
    try {
      setLoading(true)
     const newTask = await createTask({ task });
      setTimeout(() => {
        notifications.show({
          id: 'success-notification',
          autoClose: 2000,
          title: "Success",
          message: 'in creating your task',
          color: 'green',
        });
        setTasks(prev => [newTask, ...prev])
        setTask('')
        setLoading(false)
      }, 1000)
    } 
    catch (error) {
      setTimeout(() => {
        notifications.show({
          id: 'error-notification',
          autoClose: 2000,
          title: "Error",
          message: 'in creating your task',
          color: 'red',
        });
        setLoading(false)
      }, 1000)
    }
  }
  

  return (
    <div className={styles['containerInput']}>
      <Input onChange={handleOnChange} value={task} style={{ flex: 1 }} placeholder='Add your task' />
      {
        loading ? <Loader color='#5F432C' size={25} />
          :
          <button onClick={handleCreateTask}>
            <BsPlusCircle color='#5F432C' size={30} />
          </button>
      }
    </div>
  )
}