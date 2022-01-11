<template id="summary">
  <app-frame>
    <div>
      Ruilbieb:

            <table>
              <tbody>
              <tr v-for="action in actions">
                <td>{{action.timestampString}}</td>
                <td>{{action.actionString}}</td>
              </tr>
              </tbody>
            </table>

    </div>
  </app-frame>
</template>

<style>
</style>

<script>

Vue.component("summary", {
  template: "#summary",
  data: () => ({
    actions: {}
  }),

  created() {
    this.load()
  },
  methods: {
    load: function (event) {
      fetch(`/api/game/load`)
          .then(res => res.text())
          .then(text => this.actions = JSON.parse(text))
          .catch(err => alert(err));
    },
  }
});
</script>
