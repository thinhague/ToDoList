import '@/styles/globals.css'
import { MantineProvider } from '@mantine/core';
import type { AppProps } from 'next/app'
import { Notifications } from '@mantine/notifications'
export default function App({ Component, pageProps }: AppProps) {
  return (
    <MantineProvider
        withGlobalStyles
        withNormalizeCSS
        theme={{
          /** Put your mantine theme override here */
          colorScheme: 'light'
        }}
      >
        <Notifications position="top-right" />
        <Component {...pageProps} />
      </MantineProvider>
  )
}
