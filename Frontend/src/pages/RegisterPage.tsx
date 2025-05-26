import { useState } from 'react';
import axios from '../services/axios';
import { useNavigate } from 'react-router-dom';

export default function RegisterPage() {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleRegister = async () => {
        try {
            await axios.post('/auth/register', {
                username,
                email,
                password
            });
            alert('Cont creat cu succes!');
            navigate('/login');
        } catch {
            alert('Eroare la înregistrare. Verifică datele și încearcă din nou.');
        }
    };

    return (
        <div style={{ maxWidth: 400, margin: 'auto' }}>
            <h2>Înregistrare</h2>
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={e => setUsername(e.target.value)}
                style={{ width: '100%', marginBottom: '10px' }}
            />
            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={e => setEmail(e.target.value)}
                style={{ width: '100%', marginBottom: '10px' }}
            />
            <input
                type="password"
                placeholder="Parolă"
                value={password}
                onChange={e => setPassword(e.target.value)}
                style={{ width: '100%', marginBottom: '10px' }}
            />
            <button onClick={handleRegister}>Creează cont</button>
        </div>
    );
}
