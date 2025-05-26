import { Suspense, lazy } from 'react';
import { Routes, Route } from 'react-router-dom';
import { Box, CircularProgress, Container } from '@mui/material';

// Lazy load pages for better performance
const HomePage = lazy(() => import('./pages/HomePage'));
const AdminDashboardPage = lazy(() => import('./pages/AdminDashboardPage'));
const ProductManagementPage = lazy(() => import('./pages/ProductManagementPage'));
const RestaurantListPage = lazy(() => import('./pages/RestaurantListPage'));
const RestaurantDetailPage = lazy(() => import('./pages/RestaurantDetailPage'));
const LoginPage = lazy(() => import('./pages/LoginPage'));
const RegisterPage = lazy(() => import('./pages/RegisterPage'));
const OrderPage = lazy(() => import('./pages/OrderPage'));
const MyOrdersPage = lazy(() => import('./pages/MyOrdersPage'));
const NotFoundPage = lazy(() => import('./pages/NotFoundPage'));

// Loading fallback
const LoadingFallback = () => (
    <Container sx={{ display: 'flex', justifyContent: 'center', p: 4 }}>
        <CircularProgress />
    </Container>
);

export default function App() {
    return (
        <Box sx={{ minHeight: '100vh' }}>
            <Suspense fallback={<LoadingFallback />}>
                <Routes>
                    {/* Public routes */}
                    <Route path="/" element={<HomePage />} />
                    <Route path="/restaurants" element={<RestaurantListPage />} />
                    <Route path="/restaurants/:id" element={<RestaurantDetailPage />} />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/register" element={<RegisterPage />} />
                    <Route path="/order" element={<OrderPage />} />
                    <Route path="/orders" element={<MyOrdersPage />} />

                    {/* Admin routes */}
                    <Route path="/admin" element={<AdminDashboardPage />} />
                    <Route path="/admin/products" element={<ProductManagementPage />} />

                    {/* Fallback */}
                    <Route path="*" element={<NotFoundPage />} />
                </Routes>
            </Suspense>
        </Box>
    );
}
