import { useAuth } from './useAuth';

export const useAdmin = () => {
    const { token } = useAuth();

    if (!token) return false;

    try {
        const payload = JSON.parse(atob(token.split('.')[1]));
        return payload.role === 'ADMIN';
    } catch {
        return false;
    }
};