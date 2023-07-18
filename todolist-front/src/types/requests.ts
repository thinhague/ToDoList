export interface CreateTaskRequest {
  task: string;
}

export interface UpdateTaskRequest {
  task: string
  isChecked?: boolean
}

export type QueryParams = 'checked' | 'unchecked';