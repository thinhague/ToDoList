import { Input, Loader } from '@mantine/core';
import { notifications } from '@mantine/notifications';
import { BsPlusCircle } from 'react-icons/bs'
import styles from './TaskBar.module.css'
import { Dispatch, SetStateAction, useState } from 'react'
import { createTask } from '@/services/postTodoList'
import { Task } from '@/types/todolist';
import { useForm } from "react-hook-form";

interface ITaskBar {
  setTasks: Dispatch<SetStateAction<Task[]>>;
}

interface FormData {
  task: string;
}

export const AddTaskBar = ({ setTasks }: ITaskBar) => {
  const [loading, setLoading] = useState(false)
  const { handleSubmit, register, reset } = useForm<FormData>();

  const handleCreateTask = async (data: FormData) => {
    try {
      const { task } = data
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
        reset()
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
    <form className={styles['containerInput']} onSubmit={handleSubmit(handleCreateTask)}>
      <Input
        style={{ flex: 1 }}
        placeholder='Add your task'
        {...register('task')}
        maxLength={50}
      />
      {
        loading ? <Loader color='#5F432C' size={25} />
          :
          <button type='submit'>
            <BsPlusCircle color='#5F432C' size={30} />
          </button>
      }
    </form>
  )
}