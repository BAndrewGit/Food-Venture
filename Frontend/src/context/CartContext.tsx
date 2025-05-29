import { createContext, useContext, useState, useEffect, type ReactNode } from 'react';

export type CartItem = {
    productId: number;
    name: string;
    quantity: number;
    price: number;
};

type CartContextType = {
    cartItems: CartItem[];
    addToCart: (item: CartItem) => void;
    removeFromCart: (productId: number) => void;
    clearCart: () => void;
    totalPrice: number;
};

const CartContext = createContext<CartContextType | undefined>(undefined);
export { CartContext };

export const CartProvider = ({ children }: { children: ReactNode }) => {
    const [cartItems, setCartItems] = useState<CartItem[]>([]);

    // Încarcă din localStorage la montare
    useEffect(() => {
        const stored = localStorage.getItem('cart');
        if (stored) setCartItems(JSON.parse(stored));
    }, []);

    // Salvează în localStorage la fiecare modificare
    useEffect(() => {
        localStorage.setItem('cart', JSON.stringify(cartItems));
    }, [cartItems]);

    const addToCart = (item: CartItem) => {
        setCartItems(prev => {
            const existing = prev.find(i => i.productId === item.productId);
            if (existing) {
                return prev.map(i =>
                    i.productId === item.productId ? { ...i, quantity: i.quantity + item.quantity } : i
                );
            }
            return [...prev, item];
        });
    };

    const removeFromCart = (productId: number) => {
        setCartItems(prev => prev.filter(i => i.productId !== productId));
    };

    const clearCart = () => setCartItems([]);

    const totalPrice = cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);

    return (
        <CartContext.Provider value={{ cartItems, addToCart, removeFromCart, clearCart, totalPrice }}>
            {children}
        </CartContext.Provider>
    );
};

export const useCart = () => {
    const context = useContext(CartContext);
    if (!context) throw new Error('useCart must be used within a CartProvider');
    return context;
};
