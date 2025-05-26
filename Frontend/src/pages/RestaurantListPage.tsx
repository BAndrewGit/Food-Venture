import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from '../services/axios';

type Restaurant = {
    id: number;
    name: string;
    address: string;
    cuisineType: string;
};

export default function RestaurantListPage() {
    const [restaurants, setRestaurants] = useState<Restaurant[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('http://localhost:8080/api/restaurants')
            .then(res => setRestaurants(res.data))
            .catch(() => alert('Eroare la încărcarea restaurantelor'));
    }, []);

    return (
        <div>
            <h2>Restaurante</h2>
            {restaurants.length === 0 ? (
                <p>Nu există restaurante disponibile.</p>
            ) : (
                <ul>
                    {restaurants.map(r => (
                        <li
                            key={r.id}
                            style={{ cursor: 'pointer', marginBottom: '8px' }}
                            onClick={() => navigate(`/restaurants/${r.id}`)}
                        >
                            <strong>{r.name}</strong> - {r.address} ({r.cuisineType})
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}
