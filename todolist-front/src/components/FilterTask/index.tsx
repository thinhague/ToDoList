import { ChangeEvent, Dispatch, SetStateAction, useEffect, useState } from 'react';
import styles from './FilterTask.module.css'
import { Checkbox } from '@mantine/core';
import { Task } from '@/types/todolist';
import { handleFilterTask } from './handleFilterTask';

export type FilterBy = 'checked' | 'unchecked';

interface IFilterTask {
  setTasks: Dispatch<SetStateAction<Task[]>>;
}

export const FilterTask = ({ setTasks }:IFilterTask) => {
  const [filterBy, setFilterBy] = useState<FilterBy>()
  const isChecked = filterBy === 'checked';
  const isUnchecked = filterBy === 'unchecked';

  const handleOnChange = (event: ChangeEvent<HTMLInputElement>, filterBy: FilterBy) => {
    const checked = event.target.checked
    if(checked){
      setFilterBy(filterBy)
      handleFilterTask(filterBy)
      .then(res => setTasks(res?.content))
      return
    }
    setFilterBy(undefined)
    handleFilterTask()
    .then(res => setTasks(res?.content))
  }

  return (
    <div className={styles['containerFilter']}>
      <p>Filter by: </p>
      <div>
        <span>Checked: </span>
        <Checkbox
          classNames={{
            input: 'inputCheckbox'
          }}
          checked={isChecked}
          onChange={(event) => handleOnChange(event, 'checked')}
        />
      </div>
      <div>
        <span>Unchecked: </span>
        <Checkbox
          classNames={{
            input: 'inputCheckbox'
          }}
          checked={isUnchecked}
          onChange={(event) => handleOnChange(event,'unchecked')}
        />
      </div>
    </div>
  )
}