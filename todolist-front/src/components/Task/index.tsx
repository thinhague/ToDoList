import { ChangeEvent, Dispatch, SetStateAction, useState } from 'react'
import { List, Checkbox } from '@mantine/core';
import styles from './Task.module.css'
import type { Task } from '../../types/todolist'
import { updateTask } from '@/services/updateToDoList';
import { UpdateTaskRequest } from '@/types/requests'
import { ModalUpdate } from '../ModalUpdate';
import { ModalDelete } from '../ModalDelete';

interface IProps {
  setTasks: Dispatch<SetStateAction<Task[]>>;
}

export function Task(props: Task & IProps) {
  const { isChecked, task, id, setTasks, createdAt } = props
  const [checked, setChecked] = useState(isChecked);

  const handleOnChangeCheckbox = async (event: ChangeEvent<HTMLInputElement>) => {
    try {
      const eventCheckboxChecked = event.target.checked
      const body: UpdateTaskRequest = {
        task: task,
        isChecked: eventCheckboxChecked
      }
      await updateTask(id, body);
      setChecked(eventCheckboxChecked)
    }
    catch (error) {
      console.error(error);
    }

  }

  const d = new Date(createdAt)
  const createdAtTask = `${d.getDate()}/${d.getMonth()}/${d.getFullYear()}`

  return (
    <List.Item style={{ width: '100%' }}>
      <div className={styles['contentListItem']}>
        <Checkbox
          checked={checked}
          onChange={handleOnChangeCheckbox}
          classNames={{
            input: 'inputCheckbox'
          }}
        />
        <div
          className={styles['taskListItem']}
        >
          <span style={{ textDecoration: checked ? 'line-through' : 'none' }}>{task}</span>
          <label>{createdAtTask}</label>
          <ModalUpdate {...props} />
          <ModalDelete {...props} />
        </div>
      </div>
    </List.Item>
  )
}