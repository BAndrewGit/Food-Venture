import { Suspense } from 'react';
import { CircularProgress, Container } from '@mui/material';
import { BrowserRouter } from 'react-router-dom';
import AppRouter from './router/AppRouter';

const LoadingFallback = () => (
    <Container sx={{ display: 'flex', justifyContent: 'center', p: 4 }}>
        <CircularProgress />
    </Container>
);

export default function App() {
    return (
        <Suspense fallback={<LoadingFallback />}>
            <BrowserRouter>
                <AppRouter />
            </BrowserRouter>
        </Suspense>
    );
}
