import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from '../services/axios';

type Product = {
    id: number;
    name: string;
    price: number;
    description: string;
    available: boolean;
};

type Restaurant = {
    id: number;
    name: string;
    address: string;
    description: string;
    cuisineType: string;
};

export default function RestaurantDetailPage() {
    const { id } = useParams();
    const [restaurant, setRestaurant] = useState<Restaurant | null>(null);
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/restaurants/${id}`)
            .then(res => setRestaurant(res.data))
            .catch(() => alert('Eroare la încărcarea restaurantului'));

        axios.get(`http://localhost:8080/api/products/restaurant/${id}`)
            .then(res => setProducts(res.data))
            .catch(() => alert('Eroare la încărcarea produselor'));
    }, [id]);

    return (
        <div>
            {restaurant ? (
                <>
                    <h2>{restaurant.name}</h2>
                    <p>{restaurant.description}</p>
                    <p>{restaurant.address} — {restaurant.cuisineType}</p>

                    <h3>Meniu</h3>
                    {products.length === 0 ? (
                        <p>Nu există produse disponibile.</p>
                    ) : (
                        <ul>
                            {products.map(p => (
                                <li key={p.id}>
                                    {p.name} — {p.price} RON
                                    <br />
                                    <small>{p.description}</small>
                                </li>
                            ))}
                        </ul>
                    )}
                </>
            ) : (
                <p>Se încarcă datele restaurantului...</p>
            )}
        </div>
    );
}
