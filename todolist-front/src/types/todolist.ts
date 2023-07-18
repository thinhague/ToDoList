export interface ITodoListPagination {
  data:TodoListPagination;
}

export interface TodoListPagination {
  content: Task[]
  pageable: Pageable
  last: boolean
  totalPages: number
  totalElements: number
  sort: Sort
  first: boolean
  size: number
  number: number
  numberOfElements: number
  empty: boolean
}

export interface Task {
  id: number
  task: string
  isChecked: boolean
  createdAt: string
  updatedAt: string
}

export interface Pageable {
  sort: Sort
  pageNumber: number
  pageSize: number
  offset: number
  paged: boolean
  unpaged: boolean
}

export interface Sort {
  sorted: boolean
  empty: boolean
  unsorted: boolean
}
