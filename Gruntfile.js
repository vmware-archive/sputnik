module.exports = function(grunt) {

    grunt.initConfig({
        uglify: {
            my_target: {
                files: {
                    'src/main/webapp/resources/js/sputnik.min.js': [
                        'src/main/webapp/components/jquery/dist/jquery.js',
                        'src/main/webapp/components/bootstrap/dist/js/bootstrap.js',
                        'src/main/webapp/components/angular/angular.js',
                        'src/main/webapp/components/angular-resource/angular-resource.js',
                        'src/main/webapp/components/angular-route/angular-route.js',
                        'src/main/webapp/js/base.js',
                        'src/main/webapp/js/**/*.js'
                    ]
                }
            }
        },
        cssmin: {
            combine: {
                files: {
                    'src/main/webapp/resources/css/sputnik.css': [
                        'src/main/webapp/components/bootstrap/dist/css/bootstrap.css',
                        'src/main/webapp/css/base.css'
                    ]
                }
            }
        },
        watch: {
            scripts: {
                files: ['src/main/webapp/**/*.js'],
                tasks: ['uglify', 'notify:completed'],
                options: {
                    spawn: false
                }
            },
            style: {
                files: ['src/main/webapp/**/*.css'],
                tasks: ['cssmin', 'notify:completed'],
                options: {
                    spawn: false
                }
            }
        },
        notify: {
            completed: {
                options: {
                    title: 'Build completed',
                    message: 'Resources are up to date.'
                }
            }
        }
    });

    // Load the plugin that provides the "uglify" task.
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-notify');

    // Default task(s).
    grunt.registerTask('default', ['uglify', 'cssmin']);
};