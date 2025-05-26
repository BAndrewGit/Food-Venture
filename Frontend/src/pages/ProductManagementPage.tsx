import { useEffect, useState } from 'react';
import axios from '../services/axios';


interface Product {
    id: number;
    name: string;
    price: number;
    description: string;
    available: boolean;
    restaurantId?: number;
}

export default function ProductManagementPage() {
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        axios.get('/products')
            .then(res => setProducts(res.data))
            .catch(() => alert('Eroare la încărcarea produselor'));
    }, []);

    const handleDelete = async (id: number) => {
        try {
            await axios.delete(`/products/${id}`);
            setProducts(products.filter(p => p.id !== id));
        } catch {
            alert('Eroare la ștergerea produsului');
        }
    };

    return (
        <div>
            <h2>Gestionare Produse</h2>
            {products.length === 0 ? (
                <p>Nu există produse.</p>
            ) : (
                <ul>
                    {products.map(p => (
                        <li key={p.id}>
                            {p.name} - {p.price} RON - {p.available ? 'Disponibil' : 'Indisponibil'}
                            <button onClick={() => handleDelete(p.id)} style={{ marginLeft: '10px' }}>
                                Șterge
                            </button>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}
