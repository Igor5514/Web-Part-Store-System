import React, {createContext,useState} from 'react';

export const UserContext = createContext();

export const UserProvider = ({children}) => {
    const [user, setUser] = useState({
        fullName: '',
        email: '',
        phoneNumber: '',
        role: '',
        isLoggedIn: false,
        token: ''
    })

    return (
        <UserContext.Provider value = {{user, setUser}}>
            {children}
        </UserContext.Provider>
    )
}