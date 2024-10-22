import Vue from 'vue'
import VueRouter from 'vue-router'
import Nations from  '@/pages/Nations.vue';
import NationDetail from  '@/pages/NationDetail.vue';
import MilitaryUnits from  '@/pages/MilitaryUnits.vue';
import MilitaryUnitDetail from  '@/pages/MilitaryUnitDetail.vue';
import Personnels from  '@/pages/Personnels.vue';
import PersonnelDetail from  '@/pages/PersonnelDetail.vue';
import Equipments from  '@/pages/Equipments.vue';
import EquipmentDetail from  '@/pages/EquipmentDetail.vue';
import Locations from  '@/pages/Locations.vue';
import LocationDetail from  '@/pages/LocationDetail.vue';
import Movements from  '@/pages/Movements.vue';
import MovementDetail from  '@/pages/MovementDetail.vue';
import SurveillanceDatas from  '@/pages/SurveillanceDatas.vue';
import SurveillanceDataDetail from  '@/pages/SurveillanceDataDetail.vue';
import Alerts from  '@/pages/Alerts.vue';
import AlertDetail from  '@/pages/AlertDetail.vue';

Vue.use(VueRouter)

let routes = [
	{
		// will match everything
		path: '*',
		component: () => import('../views/404.vue'),
	},
	{
		path: '/',
		name: 'Home',
			redirect: '/nations',
									},
	{
		path: '/dashboard',
		name: 'Dashboard',
		layout: "dashboard",
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () => import(/* webpackChunkName: "dashboard" */ '../views/Dashboard.vue'),
	},
	{
		path: '/layout',
		name: 'Layout',
		layout: "dashboard",
		component: () => import('../views/Layout.vue'),
	},
	{
		path: '/nations',
		name: 'Nations',
		layout: "dashboard",
		component: Nations,
	},
	{
	    path: '/nation/:nationId', 
	    name: 'NationDetail',
		layout: "dashboard",
	    component: NationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/militaryUnits',
		name: 'MilitaryUnits',
		layout: "dashboard",
		component: MilitaryUnits,
	},
	{
	    path: '/militaryUnit/:militaryUnitId', 
	    name: 'MilitaryUnitDetail',
		layout: "dashboard",
	    component: MilitaryUnitDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/personnels',
		name: 'Personnels',
		layout: "dashboard",
		component: Personnels,
	},
	{
	    path: '/personnel/:personnelId', 
	    name: 'PersonnelDetail',
		layout: "dashboard",
	    component: PersonnelDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/equipments',
		name: 'Equipments',
		layout: "dashboard",
		component: Equipments,
	},
	{
	    path: '/equipment/:equipmentId', 
	    name: 'EquipmentDetail',
		layout: "dashboard",
	    component: EquipmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/locations',
		name: 'Locations',
		layout: "dashboard",
		component: Locations,
	},
	{
	    path: '/location/:locationId', 
	    name: 'LocationDetail',
		layout: "dashboard",
	    component: LocationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/movements',
		name: 'Movements',
		layout: "dashboard",
		component: Movements,
	},
	{
	    path: '/movement/:movementId', 
	    name: 'MovementDetail',
		layout: "dashboard",
	    component: MovementDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/surveillanceDatas',
		name: 'SurveillanceDatas',
		layout: "dashboard",
		component: SurveillanceDatas,
	},
	{
	    path: '/surveillanceData/:surveillanceDataId', 
	    name: 'SurveillanceDataDetail',
		layout: "dashboard",
	    component: SurveillanceDataDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/alerts',
		name: 'Alerts',
		layout: "dashboard",
		component: Alerts,
	},
	{
	    path: '/alert/:alertId', 
	    name: 'AlertDetail',
		layout: "dashboard",
	    component: AlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/requests/quickadd',
		name: 'QuickAdd',
		layout: "dashboard",
		meta: {
			title: 'quickadd',
			sidebarMap: ['applications'],
			breadcrumbs: ['Requests', 'QuickAdd'],
		},
		component: () => import('../pages/QuickAdd.vue'),
	},
	{
		path: '/tables',
		name: 'Tables',
		layout: "dashboard",
		component: () => import('../views/Tables.vue'),
	},
	{
		path: '/billing',
		name: 'Billing',
		layout: "dashboard",
		component: () => import('../views/Billing.vue'),
	},
	{
		path: '/rtl',
		name: 'RTL',
		layout: "dashboard-rtl",
		meta: {
			layoutClass: 'dashboard-rtl',
		},
		component: () => import('../views/RTL.vue'),
	},
	{
		path: '/Profile',
		name: 'Profile',
		layout: "dashboard",
		meta: {
			layoutClass: 'layout-profile',
		},
		component: () => import('../views/Profile.vue'),
	},
	{
		path: '/sign-in',
		name: 'Sign-In',
		component: () => import('../views/Sign-In.vue'),
	},
	{
		path: '/sign-up',
		name: 'Sign-Up',
		meta: {
			layoutClass: 'layout-sign-up',
		},
		component: () => import('../views/Sign-Up.vue'),
	},
]

// Adding layout property from each route to the meta
// object so it can be accessed later.
function addLayoutToRoute( route, parentLayout = "default" )
{
	route.meta = route.meta || {} ;
	route.meta.layout = route.layout || parentLayout ;
	
	if( route.children )
	{
		route.children = route.children.map( ( childRoute ) => addLayoutToRoute( childRoute, route.meta.layout ) ) ;
	}
	return route ;
}

routes = routes.map( ( route ) => addLayoutToRoute( route ) ) ;

const router = new VueRouter({
	mode: 'hash',
	base: process.env.BASE_URL,
	routes,
	scrollBehavior (to, from, savedPosition) {
		if ( to.hash ) {
			return {
				selector: to.hash,
				behavior: 'smooth',
			}
		}
		return {
			x: 0,
			y: 0,
			behavior: 'smooth',
		}
	}
})

export default router
