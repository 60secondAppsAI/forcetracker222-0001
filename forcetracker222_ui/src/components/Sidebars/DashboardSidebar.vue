<template>
	
	<!-- Main Sidebar -->
	<a-layout-sider
		collapsible
		class="sider-primary"
		breakpoint="lg"
		collapsed-width="0"
		width="250px"
		:collapsed="sidebarCollapsed"
		@collapse="$emit('toggleSidebar', ! sidebarCollapsed)"
		:trigger="null"
		:class="['ant-layout-sider-' + sidebarColor, 'ant-layout-sider-' + sidebarTheme]"
		theme="light"
		:style="{ backgroundColor: 'transparent',}">
			<div class="brand"><img src="images/drc-velocity.png" alt=""> <br><span>forcetracker222</span></div>
			<hr>

			<a-menu theme="light" mode="inline"
      			:open-keys="openKeys"
				@openChange="onOpenChange"
				>


	 
			
				<a-menu-item>
					<router-link to="/nations">
						<span class="label">Nations</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/militaryUnits">
						<span class="label">Military Units</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/personnels">
						<span class="label">Personnels</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/equipments">
						<span class="label">Equipments</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/locations">
						<span class="label">Locations</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/movements">
						<span class="label">Movements</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/surveillanceDatas">
						<span class="label">Surveillance Datas</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/alerts">
						<span class="label">Alerts</span>
					</router-link>
				</a-menu-item>
		
	

			</a-menu>

	</a-layout-sider>

</template>

<script>

	export default ({
		props: {
			// Sidebar collapsed status.
			sidebarCollapsed: {
				type: Boolean,
				default: false,
			},
			
			// Main sidebar color.
			sidebarColor: {
				type: String,
				default: "primary",
			},
			
			// Main sidebar theme : light, white, dark.
			sidebarTheme: {
				type: String,
				default: "light",
			},
		},
		data() {
			return {
      			rootSubmenuKeys: [],
				openKeys: [],
			}
		},
		methods: {
			onOpenChange(openKeys)
			{
			
				const latestOpenKey = openKeys.find( key => this.openKeys.indexOf( key ) === -1) ;

				if ( this.rootSubmenuKeys.indexOf( latestOpenKey ) === -1 )
				{
					this.openKeys = openKeys;
				}
				else
				{
					this.openKeys = latestOpenKey ? [ latestOpenKey ] : [] ;
				}
			},
		},
	})

</script>

<style>

.drcv-label {
	padding-left: 65px;
}

</style>
