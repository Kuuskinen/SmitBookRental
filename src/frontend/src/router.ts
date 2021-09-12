import Vue from 'vue'
import VueRouter, { RouteConfig } from "vue-router";

//SECURITY
//import Login from '@/components/Login.vue';
//import Register from '@/components/Register.vue';

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
    {
        path: '/',
        name: 'frontpage',
        component: () => import("./components/FrontPage.vue")
    },
    {
        path: '/books',
        name: 'books',
        component: () => import("./components/BooksForRent.vue")
    },
    {
        path: '/books_out',
        name: 'booksOut',
        component: () => import("./components/OwnerBooks.vue")
    },
    {
        path: '/add_book',
        name: 'addBook',
        component: () => import("./components/AddBook.vue")
    },
    {
        path: '/my_books',
        name: 'lenderBooks',
        component: () => import("./components/LenderBooks.vue")
    },
    {
        path: '/change_book/:id',
        name: 'changeBook',
        props: true,
        component: () => import("./components/ChangeBook.vue")
    },
    {
        path: '/rent_book/:id',
        name: 'rentBook',
        component: () => import("./components/RentBook.vue")
    },
    //SECURITY

    /*
    {
        path: '/login',
        name: 'login',
        component: () => import("./components/Login.vue")
    },
    {
        path: '/login',
        component: Login
    },
    {
        path: '/register',
        component: Register
    },
    {
        path: '/profile',
        name: 'profile',
        // lazy-loaded
        component: () => import('./components/Profile.vue')
    }
    */
    //SECURITY
    ];

const router = new VueRouter({
    base: process.env.BASE_URL,
    routes
});

//SECURITY

/*
router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/register', '/home'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    // trying to access a restricted page + not logged in
    // redirect to login page
    if (authRequired && !loggedIn) {
        next('/login');
    } else {
        next();
    }
});
 */

export default router;
