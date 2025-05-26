import { createContext, useContext, useState, type ReactNode } from 'react';

type Restaurant = {
    id: number;
    name: string;
    address?: string;
};

type RestaurantContextType = {
    selectedRestaurant: Restaurant | null;
    setSelectedRestaurant: (restaurant: Restaurant | null) => void;
};

const RestaurantContext = createContext<RestaurantContextType | undefined>(undefined);
export { RestaurantContext }; // Export explicit adăugat

export const RestaurantProvider = ({ children }: { children: ReactNode }) => {
    const [selectedRestaurant, setSelectedRestaurant] = useState<Restaurant | null>(null);

    return (
        <RestaurantContext.Provider value={{ selectedRestaurant, setSelectedRestaurant }}>
            {children}
        </RestaurantContext.Provider>
    );
};

export const useRestaurant = () => {
    const context = useContext(RestaurantContext);
    if (!context) throw new Error('useRestaurant must be used within a RestaurantProvider');
    return context;
};
