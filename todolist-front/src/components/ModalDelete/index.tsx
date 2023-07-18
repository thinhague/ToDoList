import { FiTrash2 } from 'react-icons/fi'
import { useDisclosure } from '@mantine/hooks';
import { Modal, Text, Input, Button } from '@mantine/core';
import { ChangeEvent, Dispatch, SetStateAction, useState } from 'react';
import type { Task } from '@/types/todolist';
import { notifications } from '@mantine/notifications';
import { deleteTask } from '@/services/deleteToDoList';
import styles from './ModalDelete.module.css'

interface IProps {
  setTasks: Dispatch<SetStateAction<Task[]>>;
}

export const ModalDelete = (props: Task & IProps) => {
  const { id, setTasks } = props
  const [loading, setLoading] = useState(false)
  const [opened, { open, close }] = useDisclosure(false);

  const handleDeleteTask = async () => {
    try {
      setLoading(true)
      await deleteTask(id);
  
      setTimeout(() => {
        setLoading(false)
        close()
        setTasks(prev => prev.filter(task => task.id !== id));
        notifications.show({
          id: 'success-notification',
          autoClose: 2000,
          title: "Success",
          message: 'in delete your task',
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
          message: 'in delete your task',
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
        centered
        withCloseButton={false}
        transitionProps={{ transition: 'fade', duration: 300, timingFunction: 'linear' }}
        title={
          <Text
            color='#5F432C'
            ta="center"
            fz="xl"
            fw={700}
          >
            Are you sure you want to delete this task?
          </Text>
        }
      >
        <div className={styles['contentModalDelete']}>
          <Button
            color='red'
            loading={loading}
            onClick={handleDeleteTask}
          >
            Yes
          </Button>
          <Button
            bg='#5F432C'
            onClick={close}
          >
            Cancel
          </Button>
        </div>
      </Modal>
      <button onClick={open}>
        <FiTrash2 color='#5F432C' size={20} />
      </button>
    </>
  )
}  