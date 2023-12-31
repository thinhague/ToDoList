import Head from 'next/head'
import Image from 'next/image'
import styles from '@/styles/Home.module.css'
import todolist from '../assets/todolist-image.png'
import type { Task as ITask } from '../types/todolist'
import type { InferGetServerSidePropsType, GetServerSideProps } from 'next'
import { axiosSSR } from '@/services/axios';
import { DashboardTasks } from '@/components/DashboardTasks'


export const getServerSideProps: GetServerSideProps<{
  data: ITask[]
}> = async () => {
  try{
    const data = await (await axiosSSR.get('/todolist/search?sort=createdAt,DESC')).data;
    return { props: { data: data.content } }
  }
  catch(error){
    return { props: { data: [] } }
  }
}

export default function Home({ data }:InferGetServerSidePropsType<typeof getServerSideProps>) {

  return (
    <>
      <Head>
        <title>To Do List</title>
        <meta name="description" content="Generated by create next app" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <main className={styles['main']}>
        <section>
          <Image
            src={todolist}
            alt='todolist'
          />
          <DashboardTasks data={data} />
        </section>
      </main>
    </>
  )
}

