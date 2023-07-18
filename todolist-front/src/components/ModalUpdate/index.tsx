import { AiOutlineEdit } from 'react-icons/ai'
import { useDisclosure } from '@mantine/hooks';
import { Modal, Text, Input, Button } from '@mantine/core';
import { ChangeEvent, Dispatch, SetStateAction, useState } from 'react';
import styles from './ModalUpdate.module.css'
import { updateTask } from '@/services/updateToDoList';
import type { Task } from '@/types/todolist';
import { UpdateTaskRequest } from "@/types/requests";
import { notifications } from '@mantine/notifications';

interface IProps {
  setTasks: Dispatch<SetStateAction<Task[]>>;
}

export const ModalUpdate = (props: Task & IProps) => {
  const { task, id, setTasks } = props
  const [loading, setLoading] = useState(false)
  const [opened, { open, close }] = useDisclosure(false);
  const [updateTaskInput, setUpdateTaskInput] = useState(task)
  const handleOnChange = (event: ChangeEvent<HTMLInputElement>) => setUpdateTaskInput(event.target.value)

  const handleUpdateTask = async () => {
    try {
      setLoading(true)
      const body: UpdateTaskRequest = {
        task: updateTaskInput
      }
      const updatedTask = await updateTask(id, body)
      setTimeout(() => {
        setLoading(false)
        close()
        setTasks(tasks => tasks?.map(task => {
          if (id === task.id) return updatedTask
          return task;
        })
        )
        notifications.show({
          id: 'success-notification',
          autoClose: 2000,
          title: "Success",
          message: 'in update your task',
          color: 'green',
        });
      }, 1000)
    }
    catch (error) {
      setTimeout(() => {
        notifications.show({
          id: 'error-notification',
          autoClose: 2000,
          title: "Error",
          message: 'in update your task',
          color: 'red',
        });
        setLoading(false)
      }, 1000)
    }
  }

  return (
    <>
      <Modal
        opened={opened}
        onClose={close}
        transitionProps={{ transition: 'fade', duration: 300, timingFunction: 'linear' }}
        centered
        title={
          <Text
            color='#5F432C'
            ta="center"
            fz="xl"
            fw={700}
          >
            Update your task
          </Text>
        }
      >
        <div className={styles['contentModalUpdate']}>
          <Input onChange={handleOnChange} value={updateTaskInput} style={{ flex: 1 }} />
          <Button
            bg='#5F432C'
            loading={loading}
            onClick={handleUpdateTask}
            disabled={task === updateTaskInput}
          >
            Update Task
          </Button>
        </div>
      </Modal>
      <button onClick={open}>
        <AiOutlineEdit color='#5F432C' size={24} />
      </button>
    </>
  )
}  