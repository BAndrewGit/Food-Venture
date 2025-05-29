import { Routes, Route } from 'react-router-dom';
import ProtectedRouter from './ProtectedRouter';
import Layout from '../components/Layout';

// Pages
import HomePage from '../pages/HomePage';
import LoginPage from '../pages/LoginPage';
import RegisterPage from '../pages/RegisterPage';
import OrderPage from '../pages/OrderPage';
import MyOrdersPage from '../pages/MyOrdersPage';
import AdminDashboardPage from '../pages/AdminDashboardPage';
import ProductManagementPage from '../pages/ProductManagementPage';
import RestaurantManagementPage from '../pages/RestaurantManagementPage';
import RestaurantDetailPage from '../pages/RestaurantDetailPage';
import UserManagementPage from '../pages/UserManagementPage';
import NotFoundPage from '../pages/NotFoundPage';
import CartPage from '../pages/CartPage';

const AppRouter = () => {
    return (
        <Routes>
            {/* Public pages with layout */}
            <Route element={<Layout />}>
                <Route path="/" element={<HomePage />} />
                <Route path="/restaurants/:id" element={<RestaurantDetailPage />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
            </Route>

            {/* Protected pages with layout */}
            <Route element={<ProtectedRouter />}>
                <Route element={<Layout />}>
                    <Route path="/cart" element={<CartPage />} />
                    <Route path="/order" element={<OrderPage />} />
                    <Route path="/orders" element={<MyOrdersPage />} />
                    <Route path="/admin" element={<AdminDashboardPage />} />
                    <Route path="/admin/products" element={<ProductManagementPage />} />
                    <Route path="/admin/restaurants/:id/products" element={<ProductManagementPage />} />
                    <Route path="/admin/restaurants" element={<RestaurantManagementPage />} />
                    <Route path="/profile" element={<UserManagementPage />} />
                </Route>
            </Route>

            {/* Fallback */}
            <Route path="*" element={<NotFoundPage />} />
        </Routes>
    );
};

export default AppRouter;
