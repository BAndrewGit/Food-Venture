import axios from './axios';

interface AuthResponse {
    token: string;
}

const login = async (username: string, password: string): Promise<AuthResponse> => {
    const response = await axios.post<AuthResponse>('/auth/login', {
        username,
        password,
    });
    return response.data;
};

const register = async (
    username: string,
    email: string,
    password: string
): Promise<AuthResponse> => {
    const response = await axios.post<AuthResponse>('/auth/register', {
        username,
        email,
        password,
    });
    return response.data;
};

const logout = () => {
    localStorage.removeItem('token');
    window.location.href = '/login';
};

const refreshToken = async (): Promise<AuthResponse> => {
    const response = await axios.post<AuthResponse>('/auth/refresh');
    return response.data;
};

export default {
    login,
    register,
    logout,
    refreshToken,
};
