import { Navigate, Route, Routes } from 'react-router-dom';
import {useAuth} from '../hooks/useAuth';
import HomePage from '../pages/HomePage';
// Mai adaugi pagini aici
import Layout from '../components/Layout';

const ProtectedRouter = () => {
    const { isAuthenticated } = useAuth();

    if (!isAuthenticated) {
        return <Navigate to="/login" replace />;
    }

    return (
        <Layout>
            <Routes>
                <Route path="/" element={<HomePage />} />
                {/* Ex: <Route path="/profile" element={<ProfilePage />} /> */}
            </Routes>
        </Layout>
    );
};

export default ProtectedRouter;
